import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';

import { VeiculoDecada } from './model/veiculo-decada';
import { VeiculoFabricante } from './model/veiculo-fabricante';
import { VeiculoService } from './service/veiculo.service';
import { Veiculo } from './model/veiculo';
import { Subscription } from 'rxjs';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  
  veiculosPorDecada: VeiculoDecada[] = [];
  veiculosPorFabricante: VeiculoFabricante[] = [];
  veiculosUltimaSemana: Veiculo[] = [];
  numeroVeiculosNaoVendidos: number;
  subscriptions: Subscription[] = [];

  colunas: string[] = ['veiculo', 'marca', 'ano', 'descricao', 'vendido', 'criacao', 'atualizacao'];
  dataSource: MatTableDataSource<Veiculo>;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(private veiculoService: VeiculoService) {}
  
  ngOnInit(): void {
    this.carregarVeiculosPorDecada();
    this.carregarVeiculosPorFabricante();
    this.carregarVeiculosUltimaSemanda();
    this.carregarNumeroVeiculosNaoVendidos();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  carregarVeiculosPorDecada() {
    this.subscriptions.push(
      this.veiculoService.buscarTotalVeiculosPorDecada().subscribe(result => this.veiculosPorDecada = result)
    );
  }

  carregarVeiculosPorFabricante() {
    this.subscriptions.push(
      this.veiculoService.buscarTotalVeiculosPorFabricante().subscribe(result => this.veiculosPorFabricante = result)
    );
  }

  carregarVeiculosUltimaSemanda() {
    this.subscriptions.push(
      this.veiculoService.buscarTotalVeiculosCadastradosUltimaSemana().subscribe(result => this.preencherListaVeiculos(result))
    );
  }

  carregarNumeroVeiculosNaoVendidos() {
    this.subscriptions.push(
      this.veiculoService.buscarVeiculosNaoVendidos().subscribe(result => this.numeroVeiculosNaoVendidos = result)
    );
  }

  preencherListaVeiculos(veiculos: Veiculo[]) {
    this.veiculosUltimaSemana = veiculos;
    this.dataSource = new MatTableDataSource(this.veiculosUltimaSemana);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

}
