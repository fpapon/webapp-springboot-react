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
package fr.openobject.sbr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class Controller {

    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        return ResponseEntity.ok(List.of(
                new Commande(1L, "CMD-001", "Jean Dupont",
                        LocalDate.of(2026, 5, 1), new BigDecimal("249.99"), "LIVREE"),
                new Commande(2L, "CMD-002", "Marie Martin",
                        LocalDate.of(2026, 5, 3), new BigDecimal("89.50"), "EN_COURS"),
                new Commande(3L, "CMD-003", "Pierre Bernard",
                        LocalDate.of(2026, 5, 5), new BigDecimal("1299.00"), "VALIDEE"),
                new Commande(4L, "CMD-004", "Sophie Leroy",
                        LocalDate.of(2026, 5, 7), new BigDecimal("45.00"), "EN_ATTENTE"),
                new Commande(5L, "CMD-005", "Lucas Moreau",
                        LocalDate.of(2026, 5, 9), new BigDecimal("567.80"), "LIVREE")));
    }

    public record Commande(
            Long id,
            String numero,
            String client,
            LocalDate dateCommande,
            BigDecimal montant,
            String statut){}

}
