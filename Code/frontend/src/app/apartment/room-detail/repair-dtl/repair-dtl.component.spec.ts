import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RepairDtlComponent } from './repair-dtl.component';

describe('RepairDtlComponent', () => {
  let component: RepairDtlComponent;
  let fixture: ComponentFixture<RepairDtlComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RepairDtlComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RepairDtlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
