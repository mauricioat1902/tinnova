import { environment } from '../environments/environment';
import { HttpHeaders } from '@angular/common/http';

export abstract class HttpBaseService {

  public static JSON_HEADER =
    {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

  environment = environment;

  protected constructor(private nomeApp: string) {
  }

  protected getApirUrl(): string {
    return `${this.environment.apiUrl}/${this.nomeApp}`;
  }

  protected getJsonOptions() {
    return HttpBaseService.JSON_HEADER;
  }
}
