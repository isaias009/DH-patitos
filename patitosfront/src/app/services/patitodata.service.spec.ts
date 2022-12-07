import { TestBed } from '@angular/core/testing';

import { PatitodataService } from './patitodata.service';

describe('PatitodataService', () => {
  let service: PatitodataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatitodataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
