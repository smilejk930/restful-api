package kr.app.restfulapi.sample.filtering;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

  @GetMapping("/filtering")
  public MappingJacksonValue filtering() {
    SomeBean someBean = new SomeBean("value1", "value2", "value3");

    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
    SimpleBeanPropertyFilter fileter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");// field1, field3만 보여줘라
    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", fileter);
    mappingJacksonValue.setFilters(filters);

    return mappingJacksonValue;
  }

  @GetMapping("/filtering-list")
  public MappingJacksonValue filteringList() {
    List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value3", "value4", "value5"));

    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
    SimpleBeanPropertyFilter fileter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");// field2, field3만 보여줘라
    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", fileter);
    mappingJacksonValue.setFilters(filters);

    return mappingJacksonValue;
  }
}
