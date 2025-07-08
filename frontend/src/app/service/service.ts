
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class Service {
  /**
   * Suivre les conventions REST, où les URL pour une ressource
   * "students" sont formatées comme `/api/students` et `/api/students/{id}`.
   * @private
   */

  private readonly apiUrl = environment.apiUrl;
  // URL de base pour la ressource étudiant
  private readonly studentsUrl = `${this.apiUrl}/students`;

  constructor(private http: HttpClient) { }

  /**
   * Récupère tous les étudiants
   */
  getAllStudents(): Observable<any[]> {
    return this.http.get<any[]>(this.studentsUrl);
  }

  /**
   * Crée un nouvel étudiant
   * @param student Les données du nouvel étudiant (ex: { name: 'John Doe', grade: 15 })
   */
  createStudent(student: any): Observable<any> {
    // POST sur /api/students
    return this.http.post<any>(this.studentsUrl, student);
  }

  /**
   * Supprime un étudiant par son ID
   * @param id L'ID de l'étudiant à supprimer
   */
  deleteStudent(id: number): Observable<any> {
    // DELETE sur /api/students/{id}
    return this.http.delete<any>(`${this.studentsUrl}/${id}`);
  }

  // --- Les autres méthodes du service restent inchangées ---

  getPassingStudents(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/passing`);
  }

  getStudentById(id: number): Observable<any> {
    return this.http.get<any>(`${this.studentsUrl}/${id}`);
  }

  updateStudent(id: number, student: any): Observable<any> {
    return this.http.put<any>(`${this.studentsUrl}/${id}`, student);
  }

  searchStudentsByName(name: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/search`, {
      params: { name }
    });
  }
}
