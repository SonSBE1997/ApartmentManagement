import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelComeComponent } from './cancel-come.component';

describe('CancelComeComponent', () => {
  let component: CancelComeComponent;
  let fixture: ComponentFixture<CancelComeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CancelComeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CancelComeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
