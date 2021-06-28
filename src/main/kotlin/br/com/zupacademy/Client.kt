package br.com.zupacademy

import io.grpc.ManagedChannelBuilder

fun main() {
    val channel = ManagedChannelBuilder
                                    .forAddress("localhost", 50051)
                                    .usePlaintext()
                                    .build()
    val request = FuncionarioRequest.newBuilder()
        .setNome("Magno Alvez")
        .setCpf("000.000.000-00")
        .setIdade(25)
        .setSalario(2000.20)
        .setAtivo(true)
        .setCargo(Cargo.QA)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setLogradouro("Rua Joao Luis da Silva")
            .setCep("00000-000")
            .setComplemento("Casa 20")
            .build())
        .build()

    val client = FuncionarioServiceGrpc.newBlockingStub(channel)
    val response = client.cadastrar(request)

    println(response)
}