package bulletin.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataConfig.class)
@ComponentScan(basePackages = { "bulletin" }, excludeFilters = {
		@Filter(type = FilterType.CUSTOM, value = ExcludeWebConfigFilter.class) 
	//	@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
})
public class RootConfig {
}

class ExcludeWebConfigFilter extends RegexPatternTypeFilter {
	public ExcludeWebConfigFilter() {
		super(Pattern.compile("bulletin\\.web"));
	}
}