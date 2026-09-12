import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Team {
  id?: number;
  name: string;
  owner?: { id: number };
}

@Injectable({ providedIn: 'root' })
export class TeamService {
  private baseUrl = 'http://localhost:8080/api/teams';

  constructor(private http: HttpClient) {}

  getAllTeams(): Observable<Team[]> {
    return this.http.get<Team[]>(this.baseUrl);
  }

  getTeamById(id: number): Observable<Team> {
    return this.http.get<Team>(`${this.baseUrl}/${id}`);
  }

  createTeam(team: Team): Observable<Team> {
    return this.http.post<Team>(this.baseUrl, team);
  }

  getTasksForTeam(teamId: number): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8080/api/tasks/team/${teamId}`);
  }

  getMembersForTeam(teamId: number): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8080/api/team-members/team/${teamId}`);
  }

  inviteMember(teamId: number, email: string): Observable<any> {
    return this.http.post<any>('http://localhost:8080/api/team-members/invite', { teamId, email });
  }
}
