import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RepairAptComponent } from './repair-apt.component';

describe('RepairAptComponent', () => {
  let component: RepairAptComponent;
  let fixture: ComponentFixture<RepairAptComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RepairAptComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RepairAptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
