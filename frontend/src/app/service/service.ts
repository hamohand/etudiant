import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class Service {

  private readonly apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  /**
   * Get all students
   * @returns An Observable with the list of all students
   */
  getAllStudents(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/students`);
  }

  /**
   * Get students with grades above 10
   * @returns An Observable with the list of passing students
   */
  getPassingStudents(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/passing`);
  }

  /**
   * Get a student by ID
   * @param id The student ID
   * @returns An Observable with the student details
   */
  getStudentById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  /**
   * Create a new student
   * @param student The student data
   * @returns An Observable with the created student
   */
  createStudent(student: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, student);
  }

  /**
   * Update a student
   * @param id The student ID
   * @param student The updated student data
   * @returns An Observable with the updated student
   */
  updateStudent(id: number, student: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, student);
  }

  /**
   * Delete a student
   * @param id The student ID
   * @returns An Observable with the response
   */
  deleteStudent(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }

  /**
   * Search students by name
   * @param name The name to search for
   * @returns An Observable with the list of matching students
   */
  searchStudentsByName(name: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/search`, {
      params: { name }
    });
  }
}
