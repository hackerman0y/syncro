import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { TeamService, Team } from '../../services/team';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-team-detail',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './team-detail.html',
  styleUrl: './team-detail.css',
})
export class TeamDetail implements OnInit {
  team = signal<Team | null>(null);
  members = signal<any[]>([]);
  tasks = signal<any[]>([]);
  inviteMessage = signal('');

  inviteForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
  });

  teamId!: number;

  constructor(
    private route: ActivatedRoute,
    private teamService: TeamService,
  ) {}

  ngOnInit() {
    this.teamId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadTeam();
    this.loadMembers();
    this.loadTasks();
  }

  loadTeam() {
    this.teamService.getTeamById(this.teamId).subscribe({
      next: (data) => this.team.set(data),
      error: (err) => console.error('Error loading team:', err),
    });
  }

  loadMembers() {
    this.teamService.getMembersForTeam(this.teamId).subscribe({
      next: (data) => this.members.set(data),
      error: (err) => console.error('Error loading members:', err),
    });
  }

  loadTasks() {
    this.teamService.getTasksForTeam(this.teamId).subscribe({
      next: (data) => this.tasks.set(data),
      error: (err) => console.error('Error loading tasks:', err),
    });
  }

  onInvite() {
    if (this.inviteForm.invalid) return;

    const email = this.inviteForm.value.email!;
    this.teamService.inviteMember(this.teamId, email).subscribe({
      next: () => {
        this.inviteMessage.set(`${email} was added to the team.`);
        this.inviteForm.reset();
        this.loadMembers();
      },
      error: (err) => {
        this.inviteMessage.set('Could not add that user. Check the email is correct.');
      },
    });
  }
}
