import { Component, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { EditarVeiculoComponent } from '../editar-veiculo/editar-veiculo.component';

import { Veiculo } from '../model/veiculo';
import { VeiculoService } from './../service/veiculo.service';

@Component({
  selector: 'app-lista-veiculos',
  templateUrl: './lista-veiculos.component.html',
  styleUrls: ['./lista-veiculos.component.css']
})
export class ListaVeiculosComponent implements OnInit, OnDestroy {

  colunas: string[] = ['veiculo', 'marca', 'ano', 'descricao', 'vendido', 'criacao', 'atualizacao', 'edicao'];
  dataSource: MatTableDataSource<Veiculo>;
  subscriptions: Subscription[] = [];
  veiculos: Veiculo[];
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @Input() veiculosPorInput: Veiculo[];

  constructor(private veiculoService: VeiculoService,
              private snackBar: MatSnackBar,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.carregarVeiculos();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  carregarVeiculos() {
    this.subscriptions.push(
      this.veiculoService.buscarVeiculos().subscribe(veiculos => this.preencherListaVeiculos(veiculos))
    );
  }

  preencherListaVeiculos(veiculos: Veiculo[]) {
    this.veiculos = veiculos;
    this.dataSource = new MatTableDataSource(this.veiculos);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  deletarVeiculo(veiculoId: number) {
    this.veiculoService.deletarVeiculo(veiculoId).subscribe(() => {
      this.snackBar.open('Veículo deletado');
      location.reload();
    })
  }

  abrirEdicaoVeiculo(veiculo: Veiculo): void {
    const dialogRef = this.dialog.open(EditarVeiculoComponent, {
      data: veiculo
    });
  }

}
