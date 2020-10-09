import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { MaterialModule } from './material/material.module';
import { NovoVeiculoComponent } from './novo-veiculo/novo-veiculo.component';
import { ListaVeiculosComponent } from './lista-veiculos/lista-veiculos.component';
import { EditarVeiculoComponent } from './editar-veiculo/editar-veiculo.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    NovoVeiculoComponent,
    ListaVeiculosComponent,
    EditarVeiculoComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
