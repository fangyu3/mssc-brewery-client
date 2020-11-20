package com.fangyu3.msscbreweryclient.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// Getter and Setters, hash, equal
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {

    private UUID id;
    private String beerName;
    private String beerStyle;
    private Long upc;

    public static BeerDtoBuilder builder() {
        return new BeerDtoBuilder();
    }

    public static class BeerDtoBuilder {
        private UUID id;
        private String beerName;
        private String beerStyle;
        private Long upc;

        BeerDtoBuilder() {
        }

        public BeerDtoBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public BeerDtoBuilder beerName(String beerName) {
            this.beerName = beerName;
            return this;
        }

        public BeerDtoBuilder beerStyle(String beerStyle) {
            this.beerStyle = beerStyle;
            return this;
        }

        public BeerDtoBuilder upc(Long upc) {
            this.upc = upc;
            return this;
        }

        public BeerDto build() {
            return new BeerDto(id, beerName, beerStyle, upc);
        }

        public String toString() {
            return "BeerDto.BeerDtoBuilder(id=" + this.id + ", beerName=" + this.beerName + ", beerStyle=" + this.beerStyle + ", upc=" + this.upc + ")";
        }
    }
}
