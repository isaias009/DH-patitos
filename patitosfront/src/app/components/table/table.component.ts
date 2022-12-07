import { Component } from '@angular/core';
import { PatitodataService } from 'src/app/services/patitodata.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent {

  patitoData: any;
  patitoData$: any;
  editable: number = -1;

  precio: number = -1;
  cantidad: number = -1;

  sizeData:number = 0;

  constructor(private patitoService: PatitodataService){
  }

  ngOnInit(): void{
    this.patitoData$ = this.patitoService.getData();
    this.patitoData$.subscribe((result: any) => {
      this.sizeData = result?.length;
    });
  }

  deleteData(id: number){
    this.patitoService.deletePatito(id).subscribe((result: any)=>{
      if(result){
        this.patitoData$ = this.patitoService.getData();
        this.patitoData$.subscribe((result: any) => {
          this.sizeData = result?.length;
        });
        Swal.fire(
          'Exito!',
          result.message,
          'success'
        )
      }
    });
  }

  checkEditable(id: number): void{
    this.editable = id;
    this.patitoService.getItem(id).subscribe((result: any)=>{
      this.precio = result?.precio;
      this.cantidad = result?.cantidad;
    });
  }

  editar(item: any): void{
      if(item.cantidad !== this.cantidad || item.precio !== this.precio){
        this.patitoService.editPatito(item.id, {
          "cantidad": this.cantidad,
          "precio": this.precio
        }).subscribe((result: any)=>{
          this.patitoData$ = this.patitoService.getData();
          Swal.fire(
            'Exito!',
            result?.message,
            'success'
          )
        });
      }
      this.editable = -1;
  }

  confirmar(id: number): void{
    Swal.fire({
      title: '¡Confirmacion!',
      text: "¿Esta seguro de eliminar este patito?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#2E274B',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, Eliminar patito!',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.deleteData(id);
      }
    })
  }

}
