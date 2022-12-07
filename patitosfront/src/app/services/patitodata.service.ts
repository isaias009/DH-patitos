import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PatitodataService {

  private url: string = "http://localhost:8080";

  constructor(private http: HttpClient){
  }

  getData(){
    return this.http.get(`${this.url}/patitos/orderby/cantidad`)
  }

  getItem(id: number){
    return this.http.get(`${this.url}/patito/${id}`)
  }

  createPatito(data: any){
    return this.http.post(`${this.url}/patitos/create`, data)
  }

  deletePatito(id: number){
    return this.http.delete(`${this.url}/patitos/delete/${id}`)
  }

  editPatito(id: number, data: any){
    return this.http.put(`${this.url}/patitos/update/${id}`, data)
  }
}
