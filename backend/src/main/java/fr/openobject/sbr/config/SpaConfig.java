/*
 * Copyright (c) 2026 - present - Francois Papon - https://github.com/fpapon
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package fr.openobject.sbr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class SpaConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/public/")
                .resourceChain(true)
                .addResolver(new SpaPathResourceResolver());
    }

    private static class SpaPathResourceResolver extends PathResourceResolver {
        @Override
        protected Resource getResource(String resourcePath, Resource location) throws IOException {
            Resource requestedResource = location.createRelative(resourcePath);

            // Si la ressource existe et est lisible, on la sert normalement
            if (requestedResource.exists() && requestedResource.isReadable()) {
                return requestedResource;
            }

            // Ne pas intercepter les appels API
            if (resourcePath.startsWith("api/")) {
                return null;
            }

            // Ne pas retourner index.html pour les requêtes de fichiers (avec extension)
            if (resourcePath.contains(".")) {
                return null;
            }

            // Tout le reste (routes React Router) → index.html
            return new ClassPathResource("/public/index.html");
        }
    }
}
