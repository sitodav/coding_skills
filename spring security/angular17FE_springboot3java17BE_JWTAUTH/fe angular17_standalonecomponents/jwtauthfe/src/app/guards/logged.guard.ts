import { inject } from "@angular/core";
import { CanActivateFn, Router } from "@angular/router";
import { AuthService } from "../service/auth.service";

export const LoggedInGuard : CanActivateFn = (route, state) =>
{
    let authService = inject(AuthService) ;
    let  router = inject(Router);
    
    if(!authService.isAuthenticated())
    {
        router.navigate(["/login"]);
        return false;
    }
    else
    {
        return true;
    }
};