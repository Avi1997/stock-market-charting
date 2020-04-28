import { Time } from 'highcharts';

export interface Exchange{
    id:number;
    companyCode: number;
    stockExchange: string;
    currentPrice: number,
    date: Date;
    time: Time;
}