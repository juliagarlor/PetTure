import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BuddiesComponent } from './components/buddies/buddies.component';
import { HomeComponent } from './components/home/home.component';
import { IncomingRequestsComponent } from './components/incoming-requests/incoming-requests.component';
import { LogginComponent } from './components/loggin/loggin.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SearchComponent } from './components/search/search.component';

const routes: Routes = [
  {
    path: '',
    component: LogginComponent
  },
  {
    path: 'buddyList',
    component: BuddiesComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'search',
    component: SearchComponent
  },
  {
    path: 'incoming',
    component: IncomingRequestsComponent
  },
  {
    path: 'profile',
    component: ProfileComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
