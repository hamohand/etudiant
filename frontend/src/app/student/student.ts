import {Component, OnInit} from '@angular/core';
import {Service} from '../service/service';
import {NgIf, NgFor} from "@angular/common";

@Component({
  selector: 'app-student',
  imports: [NgIf, NgFor],
  templateUrl: './student.html',
  styleUrl: './student.css'
})
export class Student implements OnInit{
  students: any[] = [];
  isLoading: boolean = false;
  error: string | null = null;

  constructor(private studentService: Service) {}

  ngOnInit(): void {
    this.loadPassingStudents();
  }

  loadPassingStudents(): void {
    this.isLoading = true;
    this.error = null;

   // this.studentService.getPassingStudents()
    this.studentService.getAllStudents()
      .subscribe({
        next: (students) => {
          this.students = students;
          console.log('Students loaded:', students);
          this.isLoading = false;
        },
        error: (err) => {
          console.error('Error loading students:', err);
          this.error = 'Une erreur est survenue lors du chargement des étudiants. Veuillez réessayer.';
          this.isLoading = false;
        }
      });
  }
}
