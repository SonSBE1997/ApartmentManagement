import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterLeaveComponent } from './register-leave.component';

describe('RegisterLeaveComponent', () => {
  let component: RegisterLeaveComponent;
  let fixture: ComponentFixture<RegisterLeaveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterLeaveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterLeaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
