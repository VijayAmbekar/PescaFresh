import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { AuthGuardService } from './services/auth-guard.service';

const routes: Routes = [
    {path: 'login', component: LoginComponent},
    // {path: '', redirectTo: '/home', pathMatch: 'full'},
    {path: '', component: HomeComponent, canActivate: [AuthGuardService]}
  ];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }