import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociationDetail } from './association-detail';

describe('AssociationDetail', () => {
  let component: AssociationDetail;
  let fixture: ComponentFixture<AssociationDetail>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssociationDetail]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssociationDetail);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
