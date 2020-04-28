import { BoardOfDirectors } from './BoardOfDirectors';
import { Sector } from './Sector';
import { StockExchange } from './StockExchange';

export interface Company {
        id: number;
        companyCode: number;
        name: string;
        turnover: number;
        ceo: string;
        boardOfDirectorsList: BoardOfDirectors[];
        listed: boolean;
        sector: Sector;
        aboutCompany: string;
        stockExchanges: StockExchange[];
}