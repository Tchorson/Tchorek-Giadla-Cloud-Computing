import { Component, OnInit, EventEmitter , Input, Output, ViewChild} from '@angular/core';
import {CronJob} from 'cron';
import {Router} from '@angular/router';
import {MatTableDataSource} from '@angular/material/table';
import {weather} from '../../models/weather';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})
export class DataComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }
  dailyContainer: MatTableDataSource<weather>;

  dailyHeaders = ['date', 'location', 'tornadoLvl'];


  @Input() dailyData: weather;
  @Output() dailyDataEmitter: EventEmitter<weather[]> = new EventEmitter();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dailyContainer.filter = filterValue;
  }

  ngOnInit(): void {
    const job = new CronJob('0/10 * * * * *', () => {
      this.importWeatherData();
    }, null, true, 'Europe/Warsaw');
    job.start();
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  importWeatherData() {
    this.http.get<weather[]>('http://localhost:8099/getAllRecords', this.httpOptions)
      .subscribe(weatherJsonArray => {
        console.log(weatherJsonArray);
        this.dailyContainer = new MatTableDataSource(weatherJsonArray.map(weatherJson => {
          return {
          date: weatherJson.date,
          location: weatherJson.location,
          latitude: weatherJson.latitude,
          longitude: weatherJson.longitude,
          dTemp: weatherJson.dTemp,
          windSpeed: weatherJson.windSpeed,
          humidity: weatherJson.humidity,
          tornadoLvl: weatherJson.tornadoLvl
          };
        }));
        this.dailyContainer.paginator = this.paginator;
        this.dailyContainer.sort = this.sort;
      });
  }
}
