import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { User } from "../model/user";
import { environment } from "src/environments/environment";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class UserRest{

  constructor(private httpClient: HttpClient){}

  createUser(email: string, password: string): Observable<User>{
    return this.httpClient.post<User>(`${environment.baseURL}user/register`,{email:email, password:password})
  }
}
