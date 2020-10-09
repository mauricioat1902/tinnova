import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { HttpBaseService } from '../http-base.service';
import { VeiculoDecada } from '../model/veiculo-decada';
import { Veiculo } from './../model/veiculo';
import { VeiculoFabricante } from './../model/veiculo-fabricante';

@Injectable({
  providedIn: 'root'
})
export class VeiculoService extends HttpBaseService {

  constructor(private httpClient: HttpClient) {
    super('veiculos');
  }

  buscarVeiculos(): Observable<Veiculo[]> {
    return this.httpClient.get<Veiculo[]>(this.getApirUrl());
  }

  buscarVeiculoPorId(veiculoId: number): Observable<Veiculo> {
    const url = `${this.getApirUrl()}/${veiculoId}`;
    return this.httpClient.get<Veiculo>(url);
  }

  salvarVeiculo(veiculo: Veiculo): Observable<Veiculo> {
    return this.httpClient.post<Veiculo>(this.getApirUrl(), veiculo);
  }

  deletarVeiculo(veiculoId: number): Observable<any> {
    const url = `${this.getApirUrl()}/${veiculoId}`;
    return this.httpClient.delete(url);
  }

  atualizarVeiculo(veiculoId: number, veiculo: Veiculo): Observable<Veiculo> {
    const url = `${this.getApirUrl()}/${veiculoId}`;
    return this.httpClient.put<Veiculo>(url, veiculo);
  }

  buscarVeiculosNaoVendidos(): Observable<number> {
    return this.httpClient.get<number>(`${this.getApirUrl()}/nao-vendidos`);
  }

  buscarTotalVeiculosPorDecada(): Observable<VeiculoDecada[]> {
    return this.httpClient.get<VeiculoDecada[]>(`${this.getApirUrl()}/decada`);
  }

  buscarTotalVeiculosPorFabricante(): Observable<VeiculoFabricante[]> {
    return this.httpClient.get<VeiculoFabricante[]>(`${this.getApirUrl()}/distribuicao-fabricante`);
  }

  buscarTotalVeiculosCadastradosUltimaSemana(): Observable<Veiculo[]> {
    return this.httpClient.get<Veiculo[]>(`${this.getApirUrl()}/cadastro/ultima-semana`);
  }
  
}
