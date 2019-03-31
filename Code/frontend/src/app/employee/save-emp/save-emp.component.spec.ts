import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveEmpComponent } from './save-emp.component';

describe('SaveEmpComponent', () => {
  let component: SaveEmpComponent;
  let fixture: ComponentFixture<SaveEmpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SaveEmpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveEmpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
