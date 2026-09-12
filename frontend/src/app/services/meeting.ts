import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Meeting {
  id?: number;
  title: string;
  notes?: string;
  dateTime: string;
  team?: { id: number };
}

@Injectable({ providedIn: 'root' })
export class MeetingService {
  private baseUrl = 'http://localhost:8080/api/meetings';

  constructor(private http: HttpClient) {}

  getAllMeetings(): Observable<Meeting[]> {
    return this.http.get<Meeting[]>(this.baseUrl);
  }

  getMeetingsForTeam(teamId: number): Observable<Meeting[]> {
    return this.http.get<Meeting[]>(`${this.baseUrl}/team/${teamId}`);
  }

  createMeeting(meeting: Meeting): Observable<Meeting> {
    return this.http.post<Meeting>(this.baseUrl, meeting);
  }
}
