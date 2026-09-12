import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { MeetingService, Meeting } from '../../services/meeting';
import { TeamService, Team } from '../../services/team';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-meetings',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
  ],
  templateUrl: './meetings.html',
  styleUrl: './meetings.css',
})
export class Meetings implements OnInit {
  meetings = signal<Meeting[]>([]);
  teams = signal<Team[]>([]);
  showForm = signal(false);

  meetingForm = new FormGroup({
    title: new FormControl('', [Validators.required]),
    notes: new FormControl(''),
    dateTime: new FormControl('', [Validators.required]),
    teamId: new FormControl<number | null>(null, [Validators.required]),
  });

  constructor(
    private meetingService: MeetingService,
    private teamService: TeamService,
  ) {}

  ngOnInit() {
    this.loadMeetings();
    this.loadTeams();
  }

  loadMeetings() {
    this.meetingService.getAllMeetings().subscribe({
      next: (data) => this.meetings.set(data),
      error: (err) => console.error('Error loading meetings:', err),
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
    if (this.meetingForm.invalid) return;

    const newMeeting: Meeting = {
      title: this.meetingForm.value.title!,
      notes: this.meetingForm.value.notes || '',
      dateTime: this.meetingForm.value.dateTime!,
      team: { id: this.meetingForm.value.teamId! },
    };

    this.meetingService.createMeeting(newMeeting).subscribe({
      next: () => {
        this.meetingForm.reset();
        this.showForm.set(false);
        this.loadMeetings();
      },
      error: (err) => console.error('Error creating meeting:', err),
    });
  }

  formatDate(dateTime: string): string {
    return new Date(dateTime).toLocaleString('en-US', {
      weekday: 'short',
      month: 'short',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
    });
  }
}
