import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { TaskService, Task } from '../../services/task';
import { TeamService, Team } from '../../services/team';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-tasks',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
  ],
  templateUrl: './tasks.html',
  styleUrl: './tasks.css',
})
export class Tasks implements OnInit {
  tasks = signal<Task[]>([]);
  teams = signal<Team[]>([]);
  showForm = signal(false);

  taskForm = new FormGroup({
    title: new FormControl('', [Validators.required]),
    description: new FormControl(''),
    teamId: new FormControl<number | null>(null, [Validators.required]),
  });

  constructor(
    private taskService: TaskService,
    private teamService: TeamService,
  ) {}

  ngOnInit() {
    this.loadTasks();
    this.loadTeams();
  }

  loadTasks() {
    this.taskService.getAllTasks().subscribe({
      next: (data) => this.tasks.set(data),
      error: (err) => console.error('Error loading tasks:', err),
    });
  }

  loadTeams() {
    this.teamService.getAllTeams().subscribe({
      next: (data) => this.teams.set(data),
      error: (err) => console.error('Error loading teams:', err),
    });
  }

  toggleForm() {
    this.showForm.set(!this.showForm());
  }

  onSubmit() {
    if (this.taskForm.invalid) return;

    const newTask: Task = {
      title: this.taskForm.value.title!,
      description: this.taskForm.value.description || '',
      status: 'TODO',
      team: { id: this.taskForm.value.teamId! },
    };

    this.taskService.createTask(newTask).subscribe({
      next: () => {
        this.taskForm.reset();
        this.showForm.set(false);
        this.loadTasks();
      },
      error: (err) => console.error('Error creating task:', err),
    });
  }
}
