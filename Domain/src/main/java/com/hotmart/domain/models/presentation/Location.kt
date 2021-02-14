package com.hotmart.domain.models.presentation

import com.hotmart.domain.models.entities.LocationEntity


class Location(
        val id: Int,
        val name: String,
        val review: Double,
        val type: String
) {

    constructor(locationResponse: LocationEntity): this(
            locationResponse.id ?: -1,
            locationResponse.name ?: "",
            locationResponse.review ?: 0.0,
            locationResponse.type ?: ""
    )

    /**
     * Idealmente a API REST poderia informar a url da imagem a ser exibida no App para cada model.
     * Nesse caso foi considerado um padrão de imagens aleatórias em mock para simular o comportamento
     * de carregamento e exibição das imagens baseados no tipo
     */
    fun getImageUrl(): String = when (type.toLowerCase()) {
        "coworking" -> "https://scinova.com.br/principal/wp-content/uploads/2020/03/coworking_covid19_creditocoworker.com_.jpg"
        "cestaurante" -> "https://www.codemoney.com.br/site2017/wp-content/uploads/2018/11/quando-inventaram-o-restaurante.jpg"
        "cadaria" -> "https://raster-static.postmates.com/?url=com.postmates.img.prod.s3.amazonaws.com/f8295f0e-48a2-4693-84c6-403332d448e0/orig.jpg&quality=90&w=1500&h=900&mode=crop&format=jpg&v=4"
        "sucos naturais" -> "https://anoteareceita.com.br/wp-content/uploads/2020/03/receitas-de-sucos-naturais-810x506.jpg"
        "produtos naturais" -> "https://www.paversul.com.br/wp-content/uploads/2019/03/o-que-vender-em-loja-de-produtos-naturais-facebook.jpg"
        "barbearia" -> "https://imagens-revista.vivadecora.com.br/uploads/2020/11/A-luminária-trilho-traz-uma-nova-perspectiva-par-aa-decoração-de-barbearia.-Fonte-Pinterest.jpg"
        "supermercado" -> "https://www.dsop.com.br/wp-content/uploads/2017/06/educacao-financeira-supermercado-como-economizar-compras.jpg"
        "bares" -> "https://f.i.uol.com.br/fotografia/2018/05/11/15260547695af5bf71d870f_1526054769_3x2_rt.jpg"
        "cafeteria" -> "https://www.foodconnection.com.br/sites/foodconnection.com/files/styles/article_featured_retina/public/uploads/2017/07/shutterstock_483160153.jpg?itok=7qHhSp8r"
        else -> "https://img.estadao.com.br/thumbs/640/resources/jpg/7/3/1572478928637.jpg"
    }

}