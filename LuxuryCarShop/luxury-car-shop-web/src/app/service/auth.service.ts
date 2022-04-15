import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";



@Injectable({
  providedIn: 'root',
})
export class AuthService {

    constructor(private http: HttpClient) {

    }

    login(email:string, password:string ) {
        return this.http.post(`${environment.baseURL}user/authenticate`, {email: email, password: password}, {responseType:'text'})
        .subscribe(res => this.setSession(res))
    }

    private setSession(token: string) {
        localStorage.setItem('token', token);
    }

    logout() {
        localStorage.removeItem("token");
    }
}


