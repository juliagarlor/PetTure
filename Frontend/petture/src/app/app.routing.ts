
import { RouterModule } from "@angular/router";
import { AppComponent } from "./app.component";
import { HomeComponent } from "./components/home/home.component";
import { IncomingRequestsComponent } from "./components/incoming-requests/incoming-requests.component";
import { LogginComponent } from "./components/loggin/loggin.component";
import { ProfileComponent } from "./components/profile/profile.component";
import { SearchComponent } from "./components/search/search.component";

const appRoutes = [
    {
        path: '',
        component: LogginComponent,
        pathMatch: "full"
      },
      {
        path: 'home',
        component: HomeComponent,
        pathMatch: "full"
      },
      {
        path: 'search',
        component: SearchComponent,
        pathMatch: "full"
      },
      {
        path: 'incoming',
        component: IncomingRequestsComponent,
        pathMatch: "full"
      },
      {
        path: 'profile',
        component: ProfileComponent,
        pathMatch: "full"
      }
];
export const routing = RouterModule.forRoot(appRoutes);