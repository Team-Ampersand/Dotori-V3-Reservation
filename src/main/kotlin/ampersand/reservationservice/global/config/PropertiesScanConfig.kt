package ampersand.reservationservice.global.config

import ampersand.reservationservice.global.datasource.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(basePackageClasses = [DataSourceProperties::class])
class PropertiesScanConfig
