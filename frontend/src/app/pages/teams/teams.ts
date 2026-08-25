import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { TeamService, Team } from '../../services/team';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-teams',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './teams.html',
  styleUrl: './teams.css',
})
export class Teams implements OnInit {
  teams = signal<Team[]>([]);
  showForm = signal(false);

  teamForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
  });

  constructor(
    private teamService: TeamService,
    private http: HttpClient,
  ) {}

  ngOnInit() {
    this.loadTeams();
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
    if (this.teamForm.invalid) return;

    this.http.get<any>('http://localhost:8080/api/users/me').subscribe({
      next: (user) => {
        const newTeam: Team = {
          name: this.teamForm.value.name!,
          owner: { id: user.id },
        };

        this.teamService.createTeam(newTeam).subscribe({
          next: () => {
            this.teamForm.reset();
            this.showForm.set(false);
            this.loadTeams();
          },
          error: (err) => console.error('Error creating team:', err),
        });
      },
      error: (err) => console.error('Error fetching current user:', err),
    });
  }
}
