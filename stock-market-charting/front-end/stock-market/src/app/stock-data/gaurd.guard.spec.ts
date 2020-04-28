import { TestBed, async, inject } from '@angular/core/testing';

import { GaurdGuard } from './gaurd.guard';

describe('GaurdGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GaurdGuard]
    });
  });

  it('should ...', inject([GaurdGuard], (guard: GaurdGuard) => {
    expect(guard).toBeTruthy();
  }));
});
