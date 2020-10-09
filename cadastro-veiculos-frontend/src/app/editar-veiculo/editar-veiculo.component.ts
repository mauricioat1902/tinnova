import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

import { Subscription } from 'rxjs';
import { Veiculo } from '../model/veiculo';
import { VeiculoService } from './../service/veiculo.service';


@Component({
  selector: 'app-editar-veiculo',
  templateUrl: './editar-veiculo.component.html',
  styleUrls: ['./editar-veiculo.component.css']
})
export class EditarVeiculoComponent implements OnInit {

  veiculoForm: FormGroup;
  subscriptions: Subscription[] = [];
  
  constructor(public dialogRef: MatDialogRef<EditarVeiculoComponent>,
              @Inject(MAT_DIALOG_DATA) public veiculo: Veiculo,
              private snackBar: MatSnackBar,
              private veiculoService: VeiculoService) { }

  ngOnInit(): void {
    this.veiculoForm = new FormGroup({
      veiculo: new FormControl(this.veiculo.veiculo, [Validators.required]),
      marca: new FormControl(this.veiculo.marca, [Validators.required]),
      ano: new FormControl(this.veiculo.ano, [Validators.required]),
      descricao: new FormControl(this.veiculo.descricao),
      vendido: new FormControl(this.veiculo.vendido ? 'true' : 'false', [Validators.required])
    });
  }

  atualizarVeiculo() {
    if (this.veiculoForm.invalid) {
      this.snackBar.open('Campos obrigatórios não preenchidos ou inválidos', 'OK', { duration: 5000 });
      return;
    }
    const veiculo: Veiculo = this.veiculoForm.value;
    this.subscriptions.push(
      this.veiculoService.atualizarVeiculo(this.veiculo.id, veiculo).subscribe(() => {
        this.snackBar.open('Veículo atualizado');
        location.reload();
      }, error => {
        console.error(error);
        this.snackBar.open("Erro na requisição");
        this.exibirErro(error);
      })
    );
  }

  exibirErro(error: any) {
    if (error instanceof HttpErrorResponse) {
      let mensagemErro: string[] = [];
      mensagemErro.push("Ocorreu um erro na requisição:\n");
      const keys = Object.keys(error.error);
      keys.forEach(key => {
        mensagemErro.push(`Erro: ${error.error[key]}\n`);
      });
      alert(mensagemErro);
    } else {
      this.snackBar.open('Erro inesperado na requisição');

    }
  }

}
