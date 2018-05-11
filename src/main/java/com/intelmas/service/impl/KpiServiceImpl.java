package com.intelmas.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intelmas.controller.elasticsearch.dto.KpiDocument;
import com.intelmas.exception.ProcessingException;
import com.intelmas.repository.elastic.KpiRepository;
import com.intelmas.service.KpiService;

@Service
public class KpiServiceImpl implements KpiService {
	
	final String type = "generic";
	final String index = "kpis";
	
	@Autowired
	private KpiRepository KpiRepository;

	@Override
	public List<String> getLatestKpisByName(String organisation, String node) throws ProcessingException {
//		List<String> cells = new ArrayList<>();
		organisation = organisation != null ? organisation.substring(0, 1).toUpperCase() + organisation.substring(1) : "";
		
		final BoolQueryBuilder builder = boolQuery()
				.must(matchQuery("node", node))
				;
		if(!StringUtils.isBlank(organisation))
			builder.must(matchQuery("organisation", organisation));
		return getLatestKpis(builder);
		
//		return cells;
	}
	
	private List<String> getLatestKpis(final QueryBuilder builder) throws ProcessingException {
		
//		SearchQuery searchQuery = new NativeSearchQueryBuilder()
//		        .withIndices(index)
//		        .withTypes(type)
//		        .withQuery(builder)
//		        .build();
		
		SearchRequest searchRequest = new SearchRequest(index);
		searchRequest.types(type);
		
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(builder);
		searchSourceBuilder.from(0).size(96);
		searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
		searchSourceBuilder.sort(new FieldSortBuilder("datetime").order(SortOrder.ASC));
		
		searchRequest.source(searchSourceBuilder);
//		searchRequest.routing(StringUtils.substring(node, 0, 3));
		
		List<KpiDocument> kpiDocuments = KpiRepository.queryForList(searchRequest);
		List<String> cells = new ArrayList<>();
		
		
		if(kpiDocuments == null) return null;
		
		kpiDocuments.forEach( kpiDocument -> {
			cells.add(kpiDocument.getCell());
		});
		
		return cells.stream().distinct().collect(Collectors.toList());
		
//		return cells;
	}

}
