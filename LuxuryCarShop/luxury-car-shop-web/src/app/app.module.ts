import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { LogInComponent } from './components/log-in/log-in.component';
import { RegisterComponent } from './components/register/register.component';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from "@angular/router";
import { CarListComponent } from './components/car-list/car-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {FormsModule} from "@angular/forms";
import { AngularMaterialModule } from "./angular-material.module";
import { HttpClientModule } from '@angular/common/http';

import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor,HttpClient } from '@angular/common/http';
import { AuthService } from './service/auth.service';
import { UserRest } from './service/user-rest.service';

@NgModule({
  declarations: [
    AppComponent,
    LogInComponent,
    RegisterComponent,
    CarListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    AngularMaterialModule,
    RouterModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  entryComponents: [
    LogInComponent
  ],
  exports:[
    AppComponent,
    LogInComponent
  ],
  providers: [AuthService, UserRest],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
