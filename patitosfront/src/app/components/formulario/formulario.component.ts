import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { PatitodataService } from '../../services/patitodata.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent {

  registerForm: FormGroup = new FormGroup({});

  constructor(private patitoService: PatitodataService, private router: Router){}

  ngOnInit(): void{
    this.registerForm = new FormGroup({
      color: new FormControl(),
      size: new FormControl(),
      precio: new FormControl(),
      cantidad: new FormControl()
    })
  }

  registerSubmited(){
    const data = this.registerForm.value;
    this.patitoService.createPatito(data).subscribe((result:any)=>{
      Swal.fire(
        'Registro exitoso',
        result.message,
        'success'
      )
      this.router.navigate(['/table']);
    });
  }

}
