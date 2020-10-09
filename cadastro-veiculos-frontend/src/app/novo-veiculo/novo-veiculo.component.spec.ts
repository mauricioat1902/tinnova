import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NovoVeiculoComponent } from './novo-veiculo.component';

describe('NovoVeiculoComponent', () => {
  let component: NovoVeiculoComponent;
  let fixture: ComponentFixture<NovoVeiculoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NovoVeiculoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NovoVeiculoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
