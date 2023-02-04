package br.com.dv.advisor.model;

import br.com.dv.advisor.data.CategoryData;

public record Category(String name, String id) implements CategoryData {

}
