import { TestBed } from '@angular/core/testing';

import { PictureServiceService } from './picture-service.service';

describe('PictureServiceService', () => {
  let service: PictureServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PictureServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
