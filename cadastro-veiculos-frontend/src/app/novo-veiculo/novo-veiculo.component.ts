import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { Veiculo } from '../model/veiculo';
import { VeiculoService } from '../service/veiculo.service';

@Component({
  selector: 'app-novo-veiculo',
  templateUrl: './novo-veiculo.component.html',
  styleUrls: ['./novo-veiculo.component.css']
})
export class NovoVeiculoComponent implements OnInit, OnDestroy {

  veiculoForm = new FormGroup({
    veiculo: new FormControl('', [Validators.required]),
    marca: new FormControl('', [Validators.required]),
    ano: new FormControl('', [Validators.required]),
    descricao: new FormControl(),
    vendido: new FormControl('', [Validators.required])
  });
  subscriptions: Subscription[] = [];

  
  constructor(private veiculoService: VeiculoService,
              private snackBar: MatSnackBar) { }
  

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  salvarVeiculo() {
    if (this.veiculoForm.invalid) {
      this.snackBar.open('Campos obrigatórios não preenchidos ou inválidos', 'OK', { duration: 5000 });
      return;
    }
    const veiculo: Veiculo = this.veiculoForm.value;
    this.subscriptions.push(
      this.veiculoService.salvarVeiculo(veiculo).subscribe(() => {
        this.snackBar.open('Veículo salvo');
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
