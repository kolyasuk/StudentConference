// package devlight.edu.conference.configuration;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
//
// @Configuration
// public class JacksonConfiguration {
//
// @Bean
// public ObjectMapper objectMapper() {
// ObjectMapper mapper = new ObjectMapper();
// mapper.registerModule(new
// Hibernate4Module().configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING,
// true));
//
// return mapper;
// }
// }