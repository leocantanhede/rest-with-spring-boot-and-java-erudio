package br.com.erudio.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {

	private static final Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();
	
	public static <O, D> D parseToObject(O origin, Class<D> destiny) {
		return MAPPER.map(origin, destiny);
	}
	
	public static <O, D> List<D> parseToListObjects(List<O> origin, Class<D> destiny) {
		List<D> destinyObjects = new ArrayList<>();
		origin.stream().forEach(o -> {
			destinyObjects.add(MAPPER.map(o, destiny));
		});
		return destinyObjects;
	}
	
}