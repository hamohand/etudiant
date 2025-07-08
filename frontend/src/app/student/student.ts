import { Component, OnInit } from '@angular/core';
import { Service } from '../service/service';
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms"; // <-- Importer FormsModule

@Component({
  selector: 'app-student',
  standalone: true, // <-- Déclarer comme standalone
  imports: [
    CommonModule, // <-- Inclut NgIf, NgFor, etc.
    FormsModule   // <-- Inclure pour utiliser [(ngModel)]
  ],
  templateUrl: './student.html',
  styleUrl: './student.css'
})
export class Student implements OnInit {
  students: any[] = [];
  isLoading: boolean = false;
  error: string | null = null;

  // Modèle pour les données du formulaire d'ajout
  newStudent = { nom: '', note: null };

  constructor(private studentService: Service) {}

  ngOnInit(): void {
    this.loadAllStudents();
  }

  loadAllStudents(): void {
    this.isLoading = true;
    this.error = null;
    this.studentService.getAllStudents().subscribe({
      next: (students) => {
        this.students = students;
        this.isLoading = false;
        console.log("students: ", students, "")
      },
      error: (err) => {
        console.error('Erreur de chargement:', err);
        this.error = 'Une erreur est survenue lors du chargement des étudiants.';
        this.isLoading = false;
      }
    });
  }

  // Méthode pour ajouter un étudiant
  addStudent(): void {
    if (!this.newStudent.nom || this.newStudent.note === null) {
      this.error = "Veuillez remplir tous les champs pour ajouter un étudiant.";
      return;
    }
    this.isLoading = true;
    this.error = null;

    this.studentService.createStudent(this.newStudent).subscribe({
      next: (createdStudent) => {
        // Ajoute le nouvel étudiant à la liste et réinitialise le formulaire
        this.students.push(createdStudent);
        this.newStudent = { nom: '', note: null };
        this.isLoading = false;
      },
      error: (err) => {
        console.error("Erreur lors de l'ajout:", err);
        this.error = "L'ajout de l'étudiant a échoué.";
        this.isLoading = false;
      }
    });
  }

  // Méthode pour supprimer un étudiant
  deleteStudentById(id: number): void {
    this.isLoading = true;
    this.error = null;

    this.studentService.deleteStudent(id).subscribe({
      next: () => {
        // Retire l'étudiant de la liste pour une mise à jour instantanée de l'UI
        this.students = this.students.filter(student => student.id !== id);
        this.isLoading = false;
      },
      error: (err) => {
        console.error("Erreur lors de la suppression:", err);
        this.error = "La suppression de l'étudiant a échoué.";
        this.isLoading = false;
      }
    });
  }
}
