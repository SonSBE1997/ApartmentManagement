import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EDetailComponent } from './e-detail.component';

describe('EDetailComponent', () => {
  let component: EDetailComponent;
  let fixture: ComponentFixture<EDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
