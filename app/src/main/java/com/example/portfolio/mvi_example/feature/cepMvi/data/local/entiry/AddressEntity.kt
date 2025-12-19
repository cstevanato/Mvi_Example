package com.example.portfolio.mvi_example.feature.cepMvi.data.local.entiry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.portfolio.mvi_example.feature.cepMvi.domain.model.Address

@Entity(tableName = "address")
data class AddressEntity(
    @PrimaryKey @ColumnInfo(name = "cep") val cep: String,
    @ColumnInfo(name = "logradouro") val logradouro: String,
    @ColumnInfo(name = "complemento") val complemento: String,
    @ColumnInfo(name = "unidade") val unidade: String,
    @ColumnInfo(name = "bairro") val bairro: String,
    @ColumnInfo(name = "localidade") val localidade: String,
    @ColumnInfo(name = "uf") val uf: String,
    @ColumnInfo(name = "estado") val estado: String,
    @ColumnInfo(name = "regiao") val regiao: String,
    @ColumnInfo(name = "ddd") val ddd: String,
)

fun AddressEntity.toCep() = Address(
    cep = cep,
    logradouro = logradouro,
    complemento = complemento,
    unidade = unidade,
    bairro = bairro,
    localidade = localidade,
    uf = uf,
    estado = estado,
    regiao = regiao,
    ddd = ddd,
)

