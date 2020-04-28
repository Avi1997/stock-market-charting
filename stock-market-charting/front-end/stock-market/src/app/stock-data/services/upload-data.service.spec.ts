import { TestBed } from '@angular/core/testing';

import { UploadDataService } from './upload-data.service';

describe('UploadDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UploadDataService = TestBed.get(UploadDataService);
    expect(service).toBeTruthy();
  });
});
